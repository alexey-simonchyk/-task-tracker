import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { UserService } from '../../services/user-service';
import { User } from '../../models/user.model';

@Component({
    selector: 'modal-window',
    templateUrl: './modal-window.component.html',
    styleUrls: ['./modal-window.component.css'],
})
export class ModalWindowComponent implements OnInit {

    @Input('developers') developers: User[];
    @Output('close') closeEvent: EventEmitter<User[]> = new EventEmitter();
    private newDevelopersList: User[];
    private allDevelopers: User[];

    constructor(private userService: UserService) { }

    ngOnInit() {
        // this.userService.getDevelopers().then((data: User[]) => this.allDevelopers = data);
        this.newDevelopersList = [...this.developers];
    }

    protected addDeveloper(developer: User) {
        this.newDevelopersList.push(developer);
    }

    protected submit() {
        this.closeEvent.emit(this.newDevelopersList);
    }

    protected close() {
        this.closeEvent.emit();
    }

}
