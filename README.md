Mobile App Store Spy
Mobile App Store Spy is an app gathering information about mobile iOS apps from Apple App Store. The main feature is providing a set of information about the app and provider. Additionally, the app is comparing the newest version of the app available in App Store with the one used by the agent and to verify whether it is still supported. As for now those versions are kept in the internal storage, however in the future they are supposed to be fetched from Tink API or tink-backend-aggregation).

Usage
The app provides the following endpoints:

http://localhost:8090/api/v1/appstore/app-details/{providerName}

to call endpoint with provider name as a parameter and retrieve basic app details from App Store

http://localhost:8090/api/v1/appstore/app-details/provider/{providerName}

to call endpoint with provider name as a parameter and retrieve all app details from App Store

http://localhost:8090/api/v1/appstore/app-details/providers/{providerNames}

to call endpoint with multiple provider names and retrieve basic apps details from App Store

http://localhost:8090/api/v1/appstore/app-details/providers/version/{providerNames}

to call endpoint with multiple provider names and retrieve all apps details from App Store

http://localhost:8090/api/v1/appstore/app-details/providers/version/all

to call endpoint and retrieve basic details from App Store for all apps

http://localhost:8090/api/v1/appstore/app-details/providers/all

to call endpoint and retrieve all details from App Store for all apps

to call endpoint with provider name as a parameter and retrieve app details from mobile app config/check endpoint

to call endpoint with multiple provider names and retrieve apps details from mobile app config/check endpoints

to call endpoint and retrieve details from mobile app config/check endpoints for all apps

to call endpoint with provider name as a parameter and get raw details from all sources about mobile app

to call endpoint with provider name as a parameter and get user friendly details from all sources about mobile app

to call endpoint with multiple provider names as a parameter and get user friendly details from all sources about mobile apps

to call endpoint and get user friendly details from all sources about all mobile app

The result is the information about:

Provider name
App name
Direct link to the app in App Store
Version currently used in the agent
The newest version available in Apple App Store
Changelog from App Store
Information about the difference between the newest app and the one used in agent
Information whether the app used in agent is still supported
Features to do in the future
E-mail notifications
Configurable scheduler for services
Obtaining versions from Tink API via endpoint
Obtaining versions from tink-backend-aggregation via endpoint
GUI
Prerequisites
IntelliJ IDEA 2020.2.4
IntelliJ Bazel Plugin 2021.02.02.0.1
Bazelisk