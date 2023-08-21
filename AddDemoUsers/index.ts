import { faker } from "@faker-js/faker"

type User = {
    userName: string
    email: string
    password: string
    roles: ("ROLE_ADMIN" | "ROLE_STUDENT" | "ROLE_TEACHER")[],
    type: "ADMIN" | "STUDENT" | "TEACHER"
}