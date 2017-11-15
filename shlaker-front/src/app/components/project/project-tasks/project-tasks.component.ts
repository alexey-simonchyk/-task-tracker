import { Component, Input, OnInit } from '@angular/core';
import { Task } from "../../../models/task.model";
import * as $ from 'jquery';
import { TaskService } from '../../../services/task-service';

@Component({
  selector: 'project-tasks',
  templateUrl: './project-tasks.component.html',
  styleUrls: ['./project-tasks.component.css']
})
export class ProjectTasksComponent implements OnInit {

    @Input("tasks") tasks: Task[];
    private draggedTask = null;

    constructor(
        private taskService: TaskService
    ) { }

    ngOnInit() {

    }

    getTasks(status) {
        return this.tasks ? this.tasks.filter(t => t.status == status) : [];
    }

    onDragStart(event, task) {
        event.dataTransfer.setData("task_id", task.id);
        this.draggedTask = task;
    }

    onDrop(event, status) {
        event.preventDefault();
        $(event.target).removeClass('drop_here');
        let data = event.dataTransfer.getData("task_id");
        // this.tasks.find(task => task.id === data).status = status;
        this.taskService.updateTaskStatus(data, status);
    }

    allowDrag(event) {
        event.preventDefault();
    }

    enterDropArea(event) {
        $(event.target).addClass('drop_here');
    }

    leaveDropArea(event) {
        $(event.target).removeClass('drop_here');
    }

}
