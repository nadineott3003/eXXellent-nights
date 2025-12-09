import { Route } from '@angular/router';
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';
import { RoomListComponent } from './features/rooms/pages/room-list/room-list.component';
import { RoomCreateComponent } from './features/rooms/pages/room-create/room-create.component';

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
      { path: '', redirectTo: 'rooms', pathMatch: 'full' },
    ],
  },
];
