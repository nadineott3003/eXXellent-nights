import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { RoomTypeDto } from '../../../../core/api/generated/models/room-type-dto';
import { RoomsService } from '../../../../core/api/generated/services/rooms.service';
import { ROOM_TYPES } from '../../../../app.config';
import { RoomUpdateDto } from '../../../../core/api/generated/models/room-update-dto';

@Component({
  standalone: true,
  selector: 'app-room-edit',
  imports: [FormsModule, RouterLink],
  templateUrl: './room-edit.component.html',
})
export class RoomEditComponent implements OnInit {
  roomNumber = '';
  roomType: RoomTypeDto | null = null;
  hasMinibar = false;

  roomTypes = ROOM_TYPES;

  errorMessage: string | null = null;
  loading = true;

  constructor(
    private route: ActivatedRoute,
    private roomsService: RoomsService,
    private router: Router
  ) {}

  ngOnInit() {
    const roomNumberParam = this.route.snapshot.paramMap.get('roomNumber');

    if (!roomNumberParam) {
      this.errorMessage = 'Ungültige Zimmernummer.';
      return;
    }

    // Zimmer laden
    this.roomsService
      .getRoom({ roomNumber: roomNumberParam })
      .then((room) => {
        this.roomNumber = room.roomNumber;
        this.roomType = room.roomType;
        this.hasMinibar = room.hasMinibar;
      })
      .catch(() => (this.errorMessage = 'Zimmer konnte nicht geladen werden.'))
      .finally(() => (this.loading = false));
  }

  submit() {
    if (!this.roomType) {
      this.errorMessage = 'Bitte füllen Sie alle Pflichtfelder aus.';
      return;
    }

    this.errorMessage = null;

    const body: RoomUpdateDto = {
      roomType: this.roomType!,
      hasMinibar: this.hasMinibar,
    };

    this.roomsService
      .updateRoom({ roomNumber: this.roomNumber, body: body })
      .then(() => this.router.navigate(['/rooms']))
      .catch((err) => {
        this.errorMessage = err?.error?.message ?? 'Es ist ein Fehler beim Speichern aufgetreten.';
      });
  }
}
