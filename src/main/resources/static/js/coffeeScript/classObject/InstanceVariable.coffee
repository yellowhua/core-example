class Zoo
  @MAX_ANIMALS: 50
  MAX_ZOOKEEPERS: 3

  helpInfos: =>
    "Zoos may contain a maximum of #{@constructor.MAX_ANIMALS} animals and #{@MAX_ZOOKEEPERS} zoo keepers."

console.log Zoo.MAX_ANIMALS
console.log Zoo.MAX_ZOOKEEPERS # => undefined
console.log Zoo::MAX_ZOOKEEPERS

zoo = new Zoo
console.log zoo.MAX_ZOOKEEPERS
console.log zoo.helpInfos()
zoo.MAX_ZOOKEEPERS = "smelly"
zoo.MAX_ANIMALS = "seventeen"
console.log zoo.helpInfos()
