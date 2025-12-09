import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { RouterOutlet } from '@angular/router';
import { initFlowbite } from 'flowbite';

@Component({
  standalone: true,
  selector: 'app-main-layout',
  imports: [HeaderComponent, RouterOutlet],
  templateUrl: './main-layout.component.html',
})
export class MainLayoutComponent implements OnInit {
  ngOnInit() {
    // Global Flowbite initialization for interactive UI elements
    initFlowbite();
  }
}
