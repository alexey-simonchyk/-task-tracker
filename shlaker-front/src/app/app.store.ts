import { Task } from './models/task.model';
import { Project } from './models/project.model';
import { DESELECT_PROJECT, LOAD_PROJECTS, SELECT_PROJECT, SELECT_TASK } from './actions';
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
        case LOAD_PROJECTS: return action.projects
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
    }
    return state;
}
