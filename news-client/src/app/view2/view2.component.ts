import { Component, ElementRef, ViewChild, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NewsService } from '../news.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view2',
  templateUrl: './view2.component.html',
  styleUrls: ['./view2.component.css']
})
export class View2Component {
  newsForm!: FormGroup
  private fb = inject(FormBuilder)
  newsSvc = inject(NewsService)
  private router = inject(Router)

  @ViewChild('toUpload')
  toUpload!: ElementRef

  addedtag = []
  tags: any[] = []
  removedtag!: String

  ngOnInit() {
    this.newsForm = this.fb.group({
      title: this.fb.control<string>('',[ Validators.required, Validators.minLength(5) ]),
      description: this.fb.control<string>('',[ Validators.required , Validators.minLength(5)]),
      tags: this.fb.control<string>(''),
    })
  }
  addtag() {
    this.addedtag = this.newsForm.value["tags"].split(/\s+/)
    this.tags.push(...this.addedtag)
    console.log(this.tags)
  }
  removetag($event: any) {
    this.removedtag = $event.target.value
    console.log(this.removedtag)
    this.tags.splice(this.tags.indexOf(this.removedtag))


  }
  back() {
    this.router.navigate(['/view0'])
  }
  processForm() {
    const value = this.newsForm.value
    this.newsSvc.upload(value["title"], this.toUpload, value["description"], this.tags)
    .then(resp => {
      console.log('>>>> resp VOODOO: ', resp)
    })
    .catch(error => {
      console.error('error: ', error)
    })
    console.log(value)

  }
}
