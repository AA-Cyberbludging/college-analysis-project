export function timestampToString(timestamp: number): string {
    return new Date(timestamp).toLocaleString();
}

export function stringToTimestamp(dateString: string): number {
    return new Date(dateString).getTime();
}

export function nowTimestamp(): number {
  return new Date().getTime();
}