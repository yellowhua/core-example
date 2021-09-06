# 克隆对象（深度复制）
  # 创建一个全新的对象
  # 这个新对象会复制原对象的所有属性，
  # 并且对原对象的所有子对象，也会递归调用克隆函数，复制每个子对象的所有属性。
clone = (obj) ->
  if not obj? or typeof obj isnt 'object'
    return obj

  if obj instanceof Date
    return new Date(obj.getTime())

  if obj instanceof RegExp
    flags = ''
    flags += 'g' if obj.global?
    flags += 'i' if obj.ignoreCase?
    flags += 'm' if obj.multiline?
    flags += 'y' if obj.sticky?
    return new RegExp(obj.source, flags)

  newInstance = new obj.constructor()
  for key of obj
    newInstance[key] = clone obj[key]
  return newInstance

x =
  foo: 'bar'
  bar: 'foo'

y = clone(x)
y.foo = 'test'

console.log x.foo isnt y.foo, x.foo, y.foo

