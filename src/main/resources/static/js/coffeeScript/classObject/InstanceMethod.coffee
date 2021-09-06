# 类方法（方法前加上@表示类方法。有点类似于java类里面的静态方法，可以直接调用）
class Songs
  @_titles: 0

  @get_count: ->
    @_titles

  constructor: (@article, @title) ->
    @constructor._titles++

console.log Songs._titles

Songs.get_count()

song = new Songs("spring", "Never Give Up")
console.log Songs.get_count()
song.get_count() # => TypeError: song.get_count is not a function



# 实例方法（有点类似java类里的private方法，需要通过new出来的实例调用）
class Songs
  _titles: 0

  get_count: ->
    @_titles

  constructor: (@article, @title) ->
    @_titles++

song = new Songs("spring", "Never Give Up")
console.log song.get_count()

Songs.get_count() # => TypeError: song.get_count is not a function