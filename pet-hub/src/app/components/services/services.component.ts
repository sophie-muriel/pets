// src/app/components/services/services.component.ts
import { Component } from '@angular/core';
import { ViewportScroller } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-services',
  templateUrl: './services.component.html'
})
export class ServicesComponent {
  constructor(
    private viewportScroller: ViewportScroller,
    private router: Router
  ) {}

  scrollToSection(elementId: string) {
    // First navigate to ensure we're on the services page
    this.router.navigate(['/services']).then(() => {
      // Then scroll to the section
      setTimeout(() => {
        this.viewportScroller.scrollToAnchor(elementId);
      }, 100);
    });
  }
}