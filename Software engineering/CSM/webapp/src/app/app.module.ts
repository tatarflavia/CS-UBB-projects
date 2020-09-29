import {BrowserModule, HammerModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import { HomeComponent } from './home/home.component';
import { NavigationComponent } from './navigation/navigation.component';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { AllConferenceComponent } from './all-conference/all-conference.component';
import {UserService} from "./service/user-service";
import { CreateConferenceComponent } from './create-conference/create-conference.component';
import {ConferenceService} from "./service/conference-service";
import { ConferenceOverviewComponent } from './conference-overview/conference-overview.component';
import { ConferenceMenuComponent } from './conference-menu/conference-menu.component';
import { PcmemberRegistrationConferenceComponent } from './pcmember-registration-conference/pcmember-registration-conference.component';
import { SubmitPaperComponent } from './submit-paper/submit-paper.component';
import { UploadAbstractComponent } from './upload-abstract/upload-abstract.component';
import { UploadFullComponent } from './upload-full/upload-full.component';
import { UploadPresentationComponent } from './upload-presentation/upload-presentation.component';
import {PaperService} from "./service/paper-service";
import {ReviewPaperComponent} from "./review-paper/review-paper.component";
import {ReviewService} from "./service/review-service";
import { ParticipateComponent } from './participate/participate.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavigationComponent,
    RegistrationComponent,
    LoginComponent,
    AllConferenceComponent,
    CreateConferenceComponent,
    ConferenceOverviewComponent,
    ConferenceMenuComponent,
    PcmemberRegistrationConferenceComponent,
    SubmitPaperComponent,
    UploadAbstractComponent,
    UploadFullComponent,
    UploadPresentationComponent,
    ReviewPaperComponent,
    ParticipateComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
  ],
  bootstrap: [AppComponent],
  providers: [
    UserService,
    ConferenceService,
    PaperService,
    ReviewService
  ]
})
export class AppModule {
}
