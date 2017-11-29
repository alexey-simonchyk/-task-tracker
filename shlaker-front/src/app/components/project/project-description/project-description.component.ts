import { Observable } from 'rxjs/Observable';
import { Component, OnInit } from '@angular/core';
import { select } from 'ng2-redux';
import { Project } from '../../../models/project.model';
import { ProjectService } from '../../../services/project-service';

@Component({
  templateUrl: './project-description.component.html',
  styleUrls: ['./project-description.component.css']
})
export class ProjectDescriptionComponent implements OnInit {

    protected isOpenedModalWindow: boolean = false;
    @select('selectedProject') selectedProject: Observable<Project>;

    constructor(private projectService: ProjectService) { }

    ngOnInit() {
    }

    protected addDeveloper() {
        this.isOpenedModalWindow = true;
    }

    protected onCloseAddDeveloperModal(developers) {
        if (developers) {
            this.selectedProject.subscribe(project => {
                this.projectService.updateProjectDevelopers(developers, project.id);
            });
        }
        this.isOpenedModalWindow = false;
    }

}
