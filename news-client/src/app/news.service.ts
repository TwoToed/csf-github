import { HttpClient } from '@angular/common/http';
import { ElementRef, Injectable, inject } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { Tags } from './tags';

@Injectable({
  providedIn: 'root'
})
export class NewsService {
  private http = inject(HttpClient)
  upload(title: string, elemRef: ElementRef, description: string, tags: string[]): Promise<any> {
    const data = new FormData()
    data.set("title", title)
    data.set("file", elemRef.nativeElement.files[0])
    data.set("description", description)
    data.set("tags", JSON.stringify(tags))
    return firstValueFrom(
      this.http.post<any>('/upload', data))
  }
  getTags() {
    return firstValueFrom(
      this.http.get<Tags[]>('tags'))
  }
}
