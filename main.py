import urllib2
import math
import random
# -*-UTF-8-*-
from com.android.monkeyrunner import MonkeyRunner as mr
from com.android.monkeyrunner import MonkeyDevice as md
from com.android.monkeyrunner import MonkeyImage as mi

image_w = 900
image_h = 720

user = ((-1, 43, 43, 73),
        (-1, 45, 45, 75),
        (-1, 46, 46, 81),
        (-1, 50, 51, 89),
        (-1, 54, 54, 94),
        (-1, 55, 56, 97),
        (-1, 55, 56, 97),
        (-1, 55, 55, 96),
        (-1, 55, 55, 96),
        (-1, 56, 55, 96),
        (-1, 57, 55, 95),
        (-1, 57, 55, 95),
        (-1, 57, 55, 95),
        (-1, 57, 55, 90),
        (-1, 56, 54, 84),
        )


class Point:
    def __init__(self, x=0, y=0):
        self.x = x
        self.y = y

    def __str__(self):
        return 'point(' + str(self.x) + ',' + str(self.y) + ')'


def is_similar(_src, _tar, _delta):
    return math.fabs(_src[1] - _tar[1]) < _delta and math.fabs(_src[2] - _tar[2]) < _delta and math.fabs(
        _src[3] - _tar[3]) < _delta


def find_user(_image):
    for j in range(image_h - 2, 0, -2):
        for i in range(image_w - 2, 75, -2):
            if is_similar(_image.getRawPixel(i, j), user[-1], 5) and is_user(_image, i, j):
                return Point(i - 38, j)


def is_user(_image, x, y):
    # print("is_user %d,%d" % (x, y))
    for i in range(14, -1, -1):
        if not is_similar(_image.getRawPixel(x - 5 * i, y), user[14 - i], 5):
            return False
    return True


def find_target(_image, x, y, _color):
    i = x
    j = y
    count = 0
    while j < y + 50:
        while i < image_w:
            if is_similar(_image.getRawPixel(i, j), _color, 10):
                count += 1
                if count >= 3:
                    return j - count
                j += 1
            else:
                count = 0
                i += 1
        if i == image_w:
            break
    return j


def find(_image):
    _color = _image.getRawPixel(360, 10)
    print("color" + str(_color))
    _p = Point()
    for j in range(10, image_h, 3):
        for i in range(10, image_w, 3):
            if not is_similar(_image.getRawPixel(i, j), _color, 10):
                _p.x = i
                _p.y = find_target(_image, i, j, _color)
                return _p


def start():
    _random = random.Random()
    count = 0
    while True:
        image = device.takeSnapshot()
        image = image.getSubImage(((1080 - image_w) / 2, (1920 - image_h) / 2, image_w, image_h))
        user_p = find_user(image)
        target_p = find(image)
        # image.writeToFile('kkk.jpg')
        print("user" + str(user_p))
        print("tar" + str(target_p))

        count += 1
        print("jump count = " + str(count))
        if user_p is None or target_p is None:
            break
        device.touch(_random.randint(360, 400), _random.randint(50, 100), md.DOWN)
        _d = math.sqrt(math.pow(user_p.x - target_p.x, 2) + math.pow(user_p.y - target_p.y, 2))
        # _time = _d * 0.00135
        # _time = math.sqrt(0.0025 * (_d + 50)) - 0.48
        _time = 0.0013 * _d + 0.03
        print("%f  %f" % (_d, _time))
        mr.sleep(_time)
        device.touch(_random.randint(360, 400), _random.randint(50, 100), md.UP)
        mr.sleep(1)


if __name__ == '__main__':
    print('start')
    device = mr.waitForConnection()
    start()
