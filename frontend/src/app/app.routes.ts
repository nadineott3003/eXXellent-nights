import { Route } from '@angular/router';
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';
import { RoomListComponent } from './features/rooms/pages/room-list/room-list.component';
import { RoomCreateComponent } from './features/rooms/pages/room-create/room-create.component';
import { RoomEditComponent } from './features/rooms/pages/room-edit/room-edit.component';
import { AvailabilityComponent } from './features/availability/pages/availability.component';

export const appRoutes: Route[] = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [
      {
        path: 'rooms',
        component: RoomListComponent,
      },
      {
        path: 'rooms/create',
        component: RoomCreateComponent,
      },
      {
        path: 'rooms/:roomNumber/edit',
        component: RoomEditComponent,
      },
      {
        path: 'availability',
        component: AvailabilityComponent,
      },
      { path: '', redirectTo: 'rooms', pathMatch: 'full' },
    ],
  },
];
