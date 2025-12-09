import {
  ApplicationConfig,
  provideBrowserGlobalErrorListeners,
  provideZoneChangeDetection,
} from '@angular/core';
import { provideRouter } from '@angular/router';
import { appRoutes } from './app.routes';
import { provideHttpClient } from '@angular/common/http';
import { provideApiConfiguration } from './core/api/generated/api-configuration';
import { RoomTypeDto } from './core/api/generated/models/room-type-dto';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(appRoutes),
    provideHttpClient(),
    provideApiConfiguration('http://localhost:8080'),
  ],
};

export const ROOM_TYPES: RoomTypeDto[] = ['SINGLE', 'DOUBLE', 'SUITE'];
