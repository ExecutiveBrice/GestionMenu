import { Component } from '@angular/core';
import { ConfigService, TransmissionService } from './services';
import { Router, ActivatedRoute } from '@angular/router';
import { TokenStorageService } from '../auth/token-storage.service';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/auth/auth.service';


@Component({
    selector: 'app',
    templateUrl: 'app.component.html',
    styleUrls: ['./app.component.css']
})

export class AppComponent {
    subscription = new Subscription()
    filterinput:String
    retour: boolean = false;

    collapse:boolean = true

    mail: boolean;
    titleText: string;
    titleDate: string;
    planing: boolean;
    organumber: number;
    isGestion: boolean
    canSendAlerte: boolean
    params: Map<string, string>

    private isAdmin: boolean;
    private loggedIn: boolean;
    private authority: string;

    constructor(
        public configService: ConfigService,
        private transmissionService: TransmissionService,
        private token: TokenStorageService,
        public router: Router,
        public route: ActivatedRoute,
        private tokenStorage: TokenStorageService) {
    }

    ngOnInit() {


        this.subscription = this.transmissionService.dataStream.subscribe(
            data => {
                console.log(data)
                this.authority = this.tokenStorage.getAuthority();
                this.loggedIn = this.tokenStorage.isLoggedIn();
                this.isAdmin = this.tokenStorage.isAdmin();
            });

        if (this.loggedIn == undefined) {
            this.authority = this.tokenStorage.getAuthority();
            this.loggedIn = this.tokenStorage.isLoggedIn();
            this.isAdmin = this.tokenStorage.isAdmin();
        }
    }

    applyFilter(){
        this.transmissionService.filterTransmission(this.filterinput);
    }

    logout() {
        this.token.signOut();
        window.location.reload();
    }

}