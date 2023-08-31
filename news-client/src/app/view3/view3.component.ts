import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view3',
  templateUrl: './view3.component.html',
  styleUrls: ['./view3.component.css']
})
export class View3Component {
  private router = inject(Router)
  view0() {
    this.router.navigate(['/view0'])
  }
}
