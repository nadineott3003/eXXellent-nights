import { Route } from '@angular/router';
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';
import { RoomListComponent } from './features/rooms/pages/room-list/room-list.component';

export const appRoutes: Route[] = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [
      {
        path: 'rooms',
        component: RoomListComponent,
      },

      { path: '', redirectTo: 'rooms', pathMatch: 'full' },
    ],
  },
];
