import { Task } from './models/task.model';
import { Project } from './models/project.model';
import {
    ADD_COMMENT_TO_PROJECT, ADD_COMMENT_TO_TASK,
    ADD_PROJECT, ADD_TASK, DESELECT_PROJECT, LOAD_PROJECTS, SELECT_PROJECT, SELECT_TASK,
    UPDATE_TASK_STATUS,
    ADD_TOKEN,
    REMOVE_TOKEN,
    ADD_USER,
    REMOVE_USER, UPDATE_TASK_DEVELOPERS, UPDATE_PROJECT_DEVELOPERS
} from './actions';
import { IAppState } from './app.store';
import { User } from './models/user.model';

const TOKEN = 'SHLAKER_TOKEN';

export interface IAppState {
    user: User;
    token: string;
    projects: Project[];
    selectedProject: Project;
    selectedTask: Task;
}

export const INITIAL_STATE: IAppState = {
    user: null,
    token: window.localStorage.getItem(TOKEN) ,
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

export function userReducer(state = null, action) {
    switch(action.type) {
        case ADD_USER: {
            return action.user;
        }
        case REMOVE_USER: return null;
    }
    return state;
}

export function selectedTaskReducer(state = null, action) {
    switch(action.type) {
        case SELECT_TASK: return action.task;
        case ADD_COMMENT_TO_TASK: {
            let newComments = state.comments.slice();
            newComments.push(action.comment);
            return {...state, comments: newComments};
        }
        case UPDATE_TASK_DEVELOPERS: {
            if (state !== null && action.taskId === state.id) {
                return {...state, developers: action.developers};
            }
            return state;
        }
    }
    return state;
}

export function tokenReducer(state = null, action) {
    switch(action.type) {
        case ADD_TOKEN: {
            window.localStorage.setItem(TOKEN, action.token);
            return action.token;
        }
        case REMOVE_TOKEN: {
            window.localStorage.removeItem(TOKEN);
            return null;
        }
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
        case ADD_COMMENT_TO_PROJECT: {
            let newComments = state.comments.slice();
            newComments.push(action.comment);
            return {...state, comments: newComments};
        }
        case UPDATE_PROJECT_DEVELOPERS: {
            return {...state, developers: action.developers};
        }
        case UPDATE_TASK_DEVELOPERS: {
            let newTask: Task = {...state.tasks.find(t => t.id === action.taskId)};
            let newTasks: Task[] = state.tasks.filter(t => t.id !== newTask.id);

            newTask.developers = action.developers;
            newTasks.push(newTask);
            return {...state, tasks: newTasks};
        }
    }
    return state;
}
