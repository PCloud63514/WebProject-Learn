import { Component } from "../decorator";

@Component
export default class Boo {
  hello() {
    console.log("Hello Boo")
  }
}