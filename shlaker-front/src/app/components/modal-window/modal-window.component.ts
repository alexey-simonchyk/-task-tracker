import { Project } from './../../models/project.model';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { UserService } from '../../services/user-service';
import { User } from '../../models/user.model';
import { NgRedux } from 'ng2-redux';
import { IAppState } from '../../app.store';

@Component({
    selector: 'modal-window',
    templateUrl: './modal-window.component.html',
    styleUrls: ['./modal-window.component.css'],
})
export class ModalWindowComponent implements OnInit {

    @Input('type') type: string;
    @Input('developers') developers: User[];
    @Output('close') closeEvent: EventEmitter<User[]> = new EventEmitter();

    protected newDevelopersList: User[];
    protected allDevelopersNotIn: User[];

    protected showDropDown: boolean = false;

    constructor(private userService: UserService, private ngRedux: NgRedux<IAppState>) { }

    ngOnInit() {
        this.newDevelopersList = [...this.developers];
        switch (this.type) {
            case 'task': {
                this.ngRedux.select('selectedProject').subscribe((project: Project) => {
                    this.allDevelopersNotIn = project.command.developers.filter((developer: User) => {
                        return this.newDevelopersList.find(temp => temp.id === developer.id) === undefined;
                    });
                });
                break;
            }
        }
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
