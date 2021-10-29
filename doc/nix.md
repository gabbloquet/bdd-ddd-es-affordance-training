# Nix install

Nix helps to provide a ready-to-develop environment without requiring any further tools, libraries or sdk to install for the project.

It provides a consistent, reproducible and strictly-identical development environment across all the team so any on-boarding is made easily as well as for non-developer team members who can easily start the application locally.

With today's polyglot development environments, each language has its own way of building and testing and Nix also helps in embracing cross-team development by providing a single way of building any projects.

Your development environment is your continuous integration environment. The same environment always and everywhere.

1. `curl -L https://nixos.org/nix/install | sh`
2. If you're on macOS > 10.5, you may be asked to run this command instead: `sh <(curl -L https://nixos.org/nix/install) --darwin-use-unencrypted-nix-store-volume`
3. `. /Users/[USERNAME]/.nix-profile/etc/profile.d/nix.sh` so nix is added to your shell profile
4. Restart your terminal and `nix --version` to assess it is installed as expected

If it doesn't work, you can add in your `~/.zshrc` (if you use a zsh shell) file :  
`if [ -e /Users/[USERNAME]/.nix-profile/etc/profile.d/nix.sh ]; then . /Users/[USERNAME]/.nix-profile/etc/profile.d/nix.sh; fi # added by Nix installer`

After, your can execute `source ~/.zshrc` to load this new configuration and retry `nix --version`.

More information [here](https://nixos.org/download.html).

# Install direnv

`direnv` is an extension for your shell which can trigger actions while landing into a directory (or its children directories) when a `.envrc` file exists.

It can be used efficiently with `nix` so anytime you enter a project directory where a nix configuration has been defined, you can use it automatically (instead of typing `nix-shell` to get the expected shell context).

1. nix-env --install direnv
2. nix-env --install nix-direnv (extension which allows caching and performance optimisation)
3. Add in your `~/.zshrc` file `eval "$(direnv hook zsh)"` (to trigger `direnv` hook in your shell)

After that you will be able to execute `direnv allow` to allow direnv to load envrc's (environment configuration) repository.

# [Optional] oh-my-zsh install

Oh My Zsh is an open source, community-driven framework for managing your [zsh](https://www.zsh.org/) configuration.

1. `sh -c "$(curl -fsSL https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"`
2. `compaudit | xargs chmod g-w,o-w`

More information [here](https://ohmyz.sh/#install).