Kotlin Player
=============

This repo contains a player service for [The Microservice Dungeon](https://github.com/The-Microservice-Dungeon), a joint Computer Science project of different study programs at [TH-Köln](https://www.th-koeln.de).

Our decisions, insights and strategy can be found [here](https://demo.hedgedoc.org/s/3-GITLjDb). 

[**Issue Board**](https://github.com/The-Microservice-Dungeon/_kotlin-player/projects/1)

**Authors**: [Jan Samak](https://github.com/Jasaka), [Andreas Ma](https://github.com/HPMinecraft), [Jasmin Blekic](https://github.com/jblekic)

## Commits

We use [semantic commit messages](https://sparkbox.com/foundry/semantic_commit_messages) to ensure a consistent style. We do this because this forces us to think about our process and because it leads to an easyly readible commit log.

### Predefined types

* `chore`: Commits related to project setup, build or continuous integration configuration
* `docs`: Commits related to documentation updates. Also applies if you update the documentation in code (jsdoc, `@doc` etc.)
* `feat`: Commits which add new features
* `fix`: Commits which fix a bug. Please add the issue id in the description if necessary
* `refact`: Commits which improve existing code without changing its functionality
* `style`: Commits which improve code style
* `test`: Commits which add/remove/improve tests

#### Examples

``` console
chore: add Oyster build script
docs: explain hat wobble
feat: add beta sequence
fix: remove broken confirmation message
refact: share logic between 4d3d3d3 and flarhgunnstow
style: convert tabs to spaces
test: ensure Tayne retains clothing
```

## **Show / Ship / Ask**, Feature Branches and Merge Requests

`Main` should always be a working version of the app, so that we can continuously deploy from it.
Development should usually happen on feature branches. These are named after their issue and kind of work done on it.
Eg. for issue [#14 - Implement `RobotService`](https://github.com/The-Microservice-Dungeon/_kotlin-player/issues/14): `/feat/14-robotservice`.

This enables a better readable git structure. If what you are working on is not directly correlating to an issue, leave out the issue number, as in: `fix/correct-behaviour-of-trader`. Always try to be descriptive in your branch names, based on what you are trying to do.


**[Show / Ship / Ask](https://martinfowler.com/articles/ship-show-ask.html)** is a branching strategy which enables fast shipping of changes.
Since we are a small team with very different time committments, this should enable a faster work pace.

### Ask
This will probably be the default most of the time.

Ask is the normal Merge Request workflow. Complete work on your feature branch, rebase it to the current state of the repo, test it, then push it to gitlab and let it pass (future) pipelines and tests. Set up a merge request, assign the team as reviewers and wait for it to be approved before merging.

### Show
If you are sure that your merge request will pass muster but still either want feedback or want to show off what you've done, create a new merge request and then instantly merge that request into `main` after it passed CI/CD.

### Ship
For very small localized changes like typos, fast hotfixes or small styling changes, that are ***definitely*** not breaking anything you can directly merge-commit your changes into `main`.

