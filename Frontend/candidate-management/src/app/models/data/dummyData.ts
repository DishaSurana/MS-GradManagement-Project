import { Candidate } from "../Candidate";
import { Degree } from "../Degree";
import { Institute } from "../Institute";
import { JobLocation } from "../JobLocation";
import { Skill } from "../Skill";

//export
const candidates: Candidate[] = [
    {
        grad_id: 'INT101',
        name: 'Ramya',
        address: 'abc-123 Pune',
        email: 'ramya@gmail.com',
        mobileNumber: '9819293844',
        degree: Degree.MCA,
        institute: <Institute>{ id: 1, name: 'DU', address: 'Delhi' },
        location: JobLocation.GURUGRAM,
        joiningDate: '2020-01-06',//new Date('2021-01-06'),
        description: 'candidate 1',
        feedback: 'good',
        skillSet: <Skill[]>[{ id: 1, skillName: 'Python' }, { id: 2, skillName: 'Java' }],
        isDeleted: false
    },
    {
        grad_id: 'INT102',
        name: 'Samaira',
        address: '12psq-123 Delhi',
        email: 'samaira@gmail.com',
        mobileNumber: '9819293844',
        degree: Degree.MSC_CS,
        institute: <Institute>{ id: 1, name: 'DU', address: 'Delhi' },
        location: JobLocation.MUMBAI,
        joiningDate: '2021-01-06', //new Date('2021-01-06'),
        description: 'candidate 2',
        feedback: 'nice',
        skillSet: <Skill[]>[{ id: 1, skillName: 'Python' }, { id: 3, skillName: 'JavaScript' }],
        isDeleted: false

    },
];

export const skills = [
    { id: 1, skillName: "C++" },
    { id: 2, skillName: "Java" },
    { id: 3, skillName: "JavaScript" }
];

export const institutes = [
    {
        "id": 1,
        "name": "Mumbai Technical University",
        "address": "Mumbai, India"
    },
    {
        "id": 2,
        "name": "Pune Technical University",
        "address": "Pune, India"
    },
];