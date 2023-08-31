import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Tags } from '../tags';
import { NewsService } from '../news.service';

@Component({
  selector: 'app-view0',
  templateUrl: './view0.component.html',
  styleUrls: ['./view0.component.css']
})
export class View0Component {
  minForm!: FormGroup
  private fb = inject(FormBuilder)
  private router = inject(Router)
  newsSvc = inject(NewsService)
  tags!: Tags[]


  ngOnInit() {
    this.minForm = this.fb.group({
      min: this.fb.control<number>(5)
    })
    this.newsSvc.getTags()
    .then(resp => {
      this.tags = resp
      console.log(this.tags[0])
    })
    .catch(error => {
      console.error('error: ', error)
    })


  }
  postNews() {
    this.router.navigate(['/view2'])
  }

  view3() {
    this.router.navigate(['/view3'])
  }
  minRec() {

  }
}
