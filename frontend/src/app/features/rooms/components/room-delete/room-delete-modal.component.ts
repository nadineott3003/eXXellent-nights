import { Component, EventEmitter, Input, Output } from '@angular/core';
import { RoomsService } from '../../../../core/api/generated/services/rooms.service';

@Component({
  standalone: true,
  selector: 'app-room-delete-modal',
  imports: [],
  templateUrl: './room-delete-modal.component.html',
})
export class RoomDeleteModal {
  @Input() roomNumber!: string;
  @Output() closed = new EventEmitter<void>();
  @Output() deleted = new EventEmitter<void>();

  errorMessage: string | null = null;
  loading = false;

  constructor(private roomsService: RoomsService) {}

  async confirmDelete() {
    this.loading = true;
    this.errorMessage = null;

    try {
      await this.roomsService.deleteRoom({ roomNumber: this.roomNumber });
      this.deleted.emit();
    } catch (err: any) {
      this.errorMessage = err?.error?.message ?? 'Löschen fehlgeschlagen.';
    }

    this.loading = false;
  }

  cancel() {
    this.closed.emit();
  }
}
