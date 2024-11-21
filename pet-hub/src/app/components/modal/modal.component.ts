import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css'],
})
export class ModalComponent {
  @Input() isVisible = false;
  @Input() title = '';
  @Input() message = '';
  @Input() confirmButtonText = 'OK';
  @Input() cancelButtonText = '';

  @Output() onConfirm = new EventEmitter<void>();
  @Output() onCancel = new EventEmitter<void>();

  confirm(): void {
    this.onConfirm.emit();
  }

  cancel(): void {
    this.onCancel.emit();
  }
}