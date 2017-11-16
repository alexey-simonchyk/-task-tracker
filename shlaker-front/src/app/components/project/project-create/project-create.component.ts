import { Component, OnInit } from '@angular/core';
import { Project } from '../../../models/project.model';
import { ProjectService } from '../../../services/project-service';
import { Router } from '@angular/router';

@Component({
  templateUrl: './project-create.component.html',
  styleUrls: ['./project-create.component.css']
})
export class ProjectCreateComponent implements OnInit {

    newProject: Project = new Project();

    constructor(
        private projectService: ProjectService,
        private router: Router
    ) { }

    ngOnInit() {
    }

    onSubmit() {
        this
            .projectService
            .createProject(this.newProject)
            .then(data => this.router.navigate(['/']));
    }

}
