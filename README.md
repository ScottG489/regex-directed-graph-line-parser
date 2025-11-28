# regex-directed-graph-line-parser
![CI](https://github.com/ScottG489/regex-directed-graph-line-parser/workflows/CI/badge.svg)

A regular expression parser for data structured on lines appearing in a consistent order.

## Development
To build and run unit tests simply run `./gradlew build`

To run the CI build locally run `./test.sh`. Be sure to fill in the correct credentials in the script if necessary.
Note that you'll need to comment out the `git clone` in `infra/build/run.sh` otherwise it will fail since you've
mounted a directory where it will attempt to clone to.

## Releasing
Releases are automatically deployed and publicly available. To release:
1. Bump `version` in `build.gradle` to a non-snapshot version
2. Commit and push
3. Bump version to next patch and append `-SNAPSHOT`
4. Commit and push

This avoids accidentally forgetting to add `-SNAPSHOT` before subsequent pushes. The build would otherwise fail because
it would refuse to overwrite the old version.
