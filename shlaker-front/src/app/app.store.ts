import { Task } from './models/task.model';
import { Project } from './models/project.model';
import {
    ADD_PROJECT, ADD_TASK, DESELECT_PROJECT, LOAD_PROJECTS, SELECT_PROJECT, SELECT_TASK,
    UPDATE_TASK_STATUS
} from './actions';
import { IAppState } from './app.store';

export interface IAppState {
    projects: Project[];
    selectedProject: Project;
    selectedTask: Task;
}

export const INITIAL_STATE: IAppState = {
    projects: [],
    selectedTask: null,
    selectedProject: null
};

export function projectReducer(state = [], action) {
    switch(action.type) {
        case LOAD_PROJECTS: return action.projects;
        case ADD_PROJECT: return [...state, action.project];
    }
    return state;
}

export function selectedTaskReducer(state = null, action) {
    switch(action.type) {
        case SELECT_TASK: return action.task;
    }
    return state;
}

export function selectedProjectReducer(state = null, action) {
    switch(action.type) {
        case SELECT_PROJECT: return action.project;
        case DESELECT_PROJECT: return null;
        case ADD_TASK: {
            let newTasks = state.tasks.slice();
            newTasks.push(action.task);
            return {...state, tasks: newTasks};
        }
        case UPDATE_TASK_STATUS: {
            let index = state.tasks.findIndex(task => task.id === action.taskId);
            let newTasks = state.tasks.slice();
            let newTask = {...newTasks[index]};
            newTask.status = action.status;
            newTasks[index] = newTask;
            return {...state, tasks: newTasks};
        }
    }
    return state;
}
