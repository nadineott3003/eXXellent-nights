import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { RoomDto } from '../../../../core/api/generated/models/room-dto';
import { RoomsService } from '../../../../core/api/generated/services/rooms.service';
import { RoomDeleteModal } from '../../components/room-delete/room-delete-modal.component';

@Component({
  standalone: true,
  selector: 'app-room-list',
  imports: [RouterLink, RoomDeleteModal],
  templateUrl: './room-list.component.html',
})
export class RoomListComponent implements OnInit {
  roomDtoArr: RoomDto[] = [];
  deleteRoomNumber: string | null = null;

  constructor(private roomsService: RoomsService) {}

  ngOnInit() {
    this.loadRooms();
  }

  async loadRooms() {
    this.roomDtoArr = await this.roomsService.getRooms();
  }

  openDeleteModal(roomNumber: string) {
    this.deleteRoomNumber = roomNumber;
  }

  closeDeleteModal() {
    this.deleteRoomNumber = null;
  }

  async handleDeleted() {
    this.deleteRoomNumber = null;
    await this.loadRooms();
  }
}
