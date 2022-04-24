import sys
import matplotlib.pyplot as plt
import numpy as np
import itertools


def plot_statistics(methods): #dict[str, tuple[list[int], list[float]]]) -> None:
    colors = itertools.cycle(['r', 'g', 'b', 'c', 'y', 'm', 'k'])
    markers = itertools.cycle(['--', '-.', ':'])
    # filled_markers = ('o', 'v', '^', '<', '>', '8', 's', 'p', '*', 'h', 'H', 'D', 'd', 'P', 'X')
    # ls = ('-', '--', '-.', ':')

    for method in methods:
        x = methods[method][0]
        y = methods[method][1]
        plt.plot(x, y, label=method, c=next(colors), ls=next(markers))

    plt.legend(loc="upper left")
    plt.show()


if __name__ == '__main__':  
    # filename = sys.argv[1]
    filename = "stats_kruskals423.txt"

    with open(f"../../../../{filename}") as f:
        lines = f.readlines()
    
    methods = {}
    while True:
        try:  
            method = lines.pop(0).split("\n")[0]
        except StopIteration:
            break
        except IndexError:
            break
        else:
            data = []
            for i in range(8):
                data.append(lines.pop(0))
        
            lines.pop(0)
        
            x = [int(d.split()[0]) for d in data]
            y = [float(d.split()[1]) for d in data]
            
            methods[method] = (x, y)
    
    print(methods)
    plot_statistics(methods)
    
#     data = np.loadtxt("data.txt")
# 
#     x = data[:, 0]
#     y = data[:, 1]
