import { Observable } from 'rxjs/Observable';
import { Component, OnInit } from '@angular/core';
import { select } from 'ng2-redux';
import { Project } from '../../../models/project.model';
import { ProjectService } from '../../../services/project-service';
import { User } from '../../../models/user.model';

@Component({
  templateUrl: './project-description.component.html',
  styleUrls: ['./project-description.component.css']
})
export class ProjectDescriptionComponent implements OnInit {

    @select('user') protected user: Observable<User>;
    protected isOpenedModalWindow: boolean = false;
    @select('selectedProject') selectedProject: Observable<Project>;
    private projectId: string;

    constructor(private projectService: ProjectService) { }

    ngOnInit() {
        this.selectedProject.subscribe(project => {
            if (project !== null) {
                this.projectId = project.id;
            }
        });
    }

    protected addDeveloper() {
        this.isOpenedModalWindow = true;
    }

    protected onCloseAddDeveloperModal(developers: User[]) {
        if (developers) {
            this.projectService.updateProjectDevelopers(developers, this.projectId);
        }
        this.isOpenedModalWindow = false;
    }

}
