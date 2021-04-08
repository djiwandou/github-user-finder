## github-user-finder
Github user finder for Android

## configuration
- Android Studio `4.1.3`
- Kotlin `v1.4.21`
- Gradle Kotlin DSL
- Android Architecture Component with modularized clean architecture approach
- Unit tested in domain, data and presentation layers

## feature
- automated kaban github-project can be found here: https://github.com/gilangabdulaziz/github-user-finder/projects/1
- apk can be downloaded here:
- sneak peak:

## curl
```curl
curl \
  -H "Accept: application/vnd.github.v3+json" \
  https://api.github.com/search/users\?q\=gigi\&since\=1\&per_page\=6
```

## known issue:
- still so much to improve (eg: unit testing is still low and UI testing not yet implemented)
