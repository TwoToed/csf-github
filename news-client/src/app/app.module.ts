import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { View2Component } from './view2/view2.component';
import { View0Component } from './view0/view0.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { View3Component } from './view3/view3.component';

const appRoutes: Routes = [
  { path: 'view2', component: View2Component, title: 'Share a News' },
  { path: '', component: View0Component, title: 'Read a News' },
  { path: "view3", component: View3Component, title: "Nothing here"},

  // default in switch, has to last
  { path: '**', redirectTo: '/', pathMatch: 'prefix' }
]
@NgModule({
  declarations: [
    AppComponent,
    View2Component,
    View0Component,
    View3Component
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
