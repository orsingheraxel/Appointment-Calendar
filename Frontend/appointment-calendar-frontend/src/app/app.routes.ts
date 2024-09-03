import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PhotoComponent } from './components/photo/photo.component';
import { ServiceComponent } from './components/service/service.component';
import { AppointmentComponent } from './components/appointment/appointment.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './auth/login/login.component';
import { CurrentWeekComponent } from './components/current-week/current-week.component';
import { ProductComponent } from './components/product/product.component';


export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'appointments', component: AppointmentComponent },
  { path: 'appointments/current-week', component: CurrentWeekComponent },
  { path: 'services', component: ServiceComponent },
  { path: 'services/:typeService', component: PhotoComponent },
  { path: 'products', component: ProductComponent },
  { path: 'login', component: LoginComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
