// jest.config.ts
import type { Config } from 'jest'

const config: Config = {
    preset: 'ts-jest',
    testEnvironment: 'node',

    testMatch: [
        '<rootDir>/tests/**/*.spec.ts'
    ],

    moduleNameMapper: {
        '^@/(.*)$': '<rootDir>/src/$1'
    },

    moduleFileExtensions: ['ts', 'js'],

    clearMocks: true
}

export default config;
