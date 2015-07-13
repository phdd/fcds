# FCDS Lab: Solutions

By Peter Heisig <peter.heisig@tu-dresden.de>. 
Get slides @[phdd.github.io/fcds](http://phdd.github.io/fcds)

Set thread pool size with e.g. `-Dgpars.poolsize=8` 

## Build

- build all project packages 
    - Unix: `./gradlew distZip`
    - Win: `gradlew.bat distZip`

- you'll find the zip-file in the *distributions* directory
  (e.g. *bucketsort/build/distributions/bucketsort.zip*)
  
## Benchmark

![Benchmark for Friendly Numbers](https://raw.githubusercontent.com/phdd/fcds/gh-pages/benchmark.png)

## License

    Copyright 2015 Peter Heisig <peter.heisig@tu-dresden.de>
    Licensed under the Apache License, Version 2.0 (the "License");
  
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
