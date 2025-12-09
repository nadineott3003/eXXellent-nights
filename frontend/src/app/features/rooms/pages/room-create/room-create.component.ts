import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { RoomsService } from '../../../../core/api/generated/services/rooms.service';
import { RoomCreateDto } from '../../../../core/api/generated/models/room-create-dto';
import { RoomTypeDto } from '../../../../core/api/generated/models/room-type-dto';
import { ROOM_TYPES } from '../../../../app.config';

@Component({
  standalone: true,
  selector: 'app-room-create',
  imports: [FormsModule, RouterLink],
  templateUrl: './room-create.component.html',
})
export class RoomCreateComponent {
  roomNumber = '';
  roomType: RoomTypeDto | null = null;
  hasMinibar = false;

  errorMessage: string | null = null;

  roomTypes = ROOM_TYPES;

  constructor(private roomsService: RoomsService, private router: Router) {}

  submit() {
    // Client-side required validation
    if (!this.roomNumber || !this.roomType) {
      this.errorMessage = 'Bitte füllen Sie alle Pflichtfelder aus.';
      return;
    }

    this.errorMessage = null;

    const body: RoomCreateDto = {
      roomNumber: this.roomNumber,
      roomType: this.roomType!,
      hasMinibar: this.hasMinibar,
    };

    this.roomsService
      .createRoom({ body })
      .then(() => this.router.navigate(['/rooms']))
      .catch((err) => {
        this.errorMessage = err?.error?.message ?? 'Es ist ein Fehler beim Speichern aufgetreten.';
      });
  }
}
