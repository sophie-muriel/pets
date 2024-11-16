// src/app/services/scroll.service.ts
import { Injectable } from '@angular/core';
import { Router, Scroll } from '@angular/router';
import { ViewportScroller } from '@angular/common';
import { filter } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ScrollService {
  constructor(
    private router: Router,
    private viewportScroller: ViewportScroller
  ) {
    // Subscribe to router scroll events
    this.router.events.pipe(
      filter((e): e is Scroll => e instanceof Scroll)
    ).subscribe(e => {
      if (e.position) {
        // Backward navigation
        this.viewportScroller.scrollToPosition(e.position);
      } else if (e.anchor) {
        // Anchor navigation
        setTimeout(() => {
          this.viewportScroller.scrollToAnchor(e.anchor as string);
        }, 100);
      } else {
        // Forward navigation
        this.viewportScroller.scrollToPosition([0, 0]);
      }
    });
  }
}