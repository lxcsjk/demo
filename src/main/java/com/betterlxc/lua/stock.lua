---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by LXC.
--- DateTime: 2019-04-22 14:15
---
if redis.call('get',KEYS[1]) then return 1 else redis.call('set', KEYS[1], 'test') return 0 end