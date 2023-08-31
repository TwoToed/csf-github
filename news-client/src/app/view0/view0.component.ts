import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view0',
  templateUrl: './view0.component.html',
  styleUrls: ['./view0.component.css']
})
export class View0Component {
  minForm!: FormGroup
  private fb = inject(FormBuilder)
  private router = inject(Router)
  ngOnInit() {
    this.minForm = this.fb.group({
      min: this.fb.control<number>(5)
    })
    
  }
  postNews() {
    this.router.navigate(['/view2'])
  }
  minRec() {

  }
}
