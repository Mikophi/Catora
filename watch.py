from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler
from subprocess import Popen
import sys

class MyHandler(FileSystemEventHandler):
    def on_any_event(self, event):
        if event.is_directory:
            return
        if event.event_type == 'modified':
            print(f'Restarting due to file change: {event.src_path}')
            Popen([sys.executable, 'app.py']).wait()

if __name__ == '__main__':
    event_handler = MyHandler()
    observer = Observer()
    observer.schedule(event_handler, '.', recursive=True)

    try:
        observer.start()
        print('Watching for changes...')
        observer.join()
    except KeyboardInterrupt:
        observer.stop()

    observer.join()
