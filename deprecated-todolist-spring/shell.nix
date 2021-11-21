let
  sources = import nix/sources.nix;
  nixpkgs = import sources.nixpkgs { };
in with nixpkgs;

mkShell {
  buildInputs = [
    maven
    jdk17
    niv # Tools to interact with Nix
  ];
}
