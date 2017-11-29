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

    protected newDevelopersList: User[];
    protected allDevelopersNotIn: User[];

    protected showDropDown: boolean = false;

    constructor(private userService: UserService) { }

    ngOnInit() {
        this.newDevelopersList = [...this.developers];
        this.userService.getDevelopers().then((data: User[]) => {
            this.allDevelopersNotIn = data.filter((developer: User) => {
                return this.newDevelopersList.indexOf(developer) !== 0;
            });
        });
    }

    protected addDeveloper(developer: User) {
        this.showDropDown = false;
        this.newDevelopersList.push(developer);

        const index = this.allDevelopersNotIn.indexOf(developer);
        if (index !== -1) {
            this.allDevelopersNotIn.splice(index, 1);
        }
    }

    protected submit() {
        this.closeEvent.emit(this.newDevelopersList);
    }

    protected close() {
        this.closeEvent.emit();
    }

    protected changeDropDownState() {
        this.showDropDown = !this.showDropDown;
    }

    protected removeDeveloper(developer: User) {
        const index = this.newDevelopersList.indexOf(developer);
        if (index !== -1) {
            this.newDevelopersList.splice(index, 1);
            this.allDevelopersNotIn.push(developer);
        }
    }

    protected disableDropDown(target) {
        if (!target.classList.contains('dropdown_button')) {
            this.showDropDown = false;
        }
    }
}
