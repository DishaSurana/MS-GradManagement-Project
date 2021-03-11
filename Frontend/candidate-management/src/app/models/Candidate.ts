import { Degree } from "./Degree";
import { Institute } from "./Institute";
import { JobLocation } from "./JobLocation";
import { Skill } from "./Skill";

export interface Candidate {
    grad_id: String,
    name: String,
    address: String,
    email: String,
    mobileNumber: String,
    degree: Degree,
    institute: Institute,
    location: JobLocation,
    joiningDate: String,
    description: String,
    feedback: String,
    skillSet: Skill[],
    isDeleted: Boolean;
}

export function checkEmptyCandidate(candidate): Boolean {
    candidate = <Candidate>candidate;

    if (candidate.grad_id === "" || candidate.name === ""
        || candidate.address === "" || candidate.email == ""
        || candidate.mobileNumber === "" || candidate.description === ""
        || candidate.feedback === "" || candidate.degree === null
        || candidate.institute === null || candidate.location === null
        || candidate.joiningDate === null
    ) {
        return false;
    }
    return true;
}