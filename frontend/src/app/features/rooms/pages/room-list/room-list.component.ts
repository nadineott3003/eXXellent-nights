import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { RoomDto } from '../../../../core/api/generated/models/room-dto';
import { RoomsService } from '../../../../core/api/generated/services/rooms.service';

@Component({
  standalone: true,
  selector: 'app-room-list',
  imports: [RouterLink],
  templateUrl: './room-list.component.html',
})
export class RoomListComponent implements OnInit {
  roomDtoArr: RoomDto[] = [];

  constructor(private roomsService: RoomsService) {}

  ngOnInit() {
    this.roomsService
      .getRooms()
      .then((rooms) => (this.roomDtoArr = rooms))
      .catch((err) => console.error(err));
  }
}
