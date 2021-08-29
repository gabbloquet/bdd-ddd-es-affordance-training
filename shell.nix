let
  sources = import nix/sources.nix;
  nixpkgs = import sources.nixpkgs { };
in with nixpkgs;

mkShell {
  buildInputs = [
    maven
    jdk16
    niv # Tools to interact with Nix
  ];
}
