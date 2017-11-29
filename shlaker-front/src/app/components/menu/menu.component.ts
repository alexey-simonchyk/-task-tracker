import { User } from '../../models/user.model';
import { Observable } from 'rxjs/Observable';
import { Component, OnInit } from '@angular/core';
import { NgRedux, select } from 'ng2-redux';
import { Project } from '../../models/project.model';
import { ProjectService } from '../../services/project-service';
import * as $ from 'jquery';
import { IAppState } from '../../app.store';
import { REMOVE_TOKEN, REMOVE_USER } from '../../actions';

@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

    @select('projects') projects: Observable<Project[]>;
    @select('selectedProject') selectedProject: Observable<Project>;
    @select('user') currentUser: Observable<User>;


    menuState = {projects: false};
    imageLink: string = '';

    constructor(private projectService: ProjectService, private ngRedux: NgRedux<IAppState>) { }

    ngOnInit() {
        this.projectService.getProjects();
    }

    changeMenuState(target) {
        let element;
        switch (target) {
            case 'projects':
                element = $('#projects_close');
                break;
        }

        if (this.menuState[target]) {
            element.slideDown('slow');
        } else {
            element.slideUp('slow');
        }

        this.menuState[target] = !this.menuState[target];
    }

    logout() {
        this.ngRedux.dispatch({type: REMOVE_USER});
        this.ngRedux.dispatch({type: REMOVE_TOKEN});
    }

}
