import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RoomTypeDto } from '../../../core/api/generated/models/room-type-dto';
import { ROOM_TYPES } from '../../../app.config';
import { RoomDto } from '../../../core/api/generated/models/room-dto';
import { AvailabilityService } from '../../../core/api/generated/services/availability.service';

@Component({
  standalone: true,
  selector: 'app-availability-page',
  imports: [FormsModule],
  templateUrl: './availability.component.html',
})
export class AvailabilityComponent {
  hasSearched = false;
  dateFrom: string = '';
  dateTo: string = '';
  filterRoomType: RoomTypeDto | null = null;
  filterMinibar: boolean | null = null;

  roomTypes = ROOM_TYPES;

  availableRooms: RoomDto[] = [];
  errorMessage: string | null = null;
  loading = false;

  constructor(private availabilityService: AvailabilityService) {}

  async search() {
    this.hasSearched = true;
    this.errorMessage = null;

    if (!this.dateFrom || !this.dateTo) {
      this.errorMessage = 'Bitte wählen Sie einen gültigen Zeitraum aus.';
      return;
    }

    this.loading = true;

    try {
      this.availableRooms = await this.availabilityService.getAvailableRooms({
        startDate: this.dateFrom,
        endDate: this.dateTo,
        hasMinibar: this.filterMinibar ?? undefined,
        roomType: this.filterRoomType ?? undefined,
      });
    } catch (err: any) {
      this.errorMessage = err?.error?.message ?? 'Fehler beim Laden der verfügbaren Zimmer.';
    } finally {
      this.loading = false;
    }
  }
}
