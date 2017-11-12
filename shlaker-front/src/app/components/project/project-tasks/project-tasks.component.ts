import { Component, Input, OnInit } from '@angular/core';
import { Task } from "../../../models/task.model";

@Component({
  selector: 'project-tasks',
  templateUrl: './project-tasks.component.html',
  styleUrls: ['./project-tasks.component.css']
})
export class ProjectTasksComponent implements OnInit {

    @Input("tasks") tasks: Task[];

    constructor() { }

    ngOnInit() {

    }

    getTasks(status) {
        return this.tasks ? this.tasks.filter(t => t.status == status) : [];
    }

}
