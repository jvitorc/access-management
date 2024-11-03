export function newPageBuild() {
  return {
    content: [],
    pageable: {
      pageNumber: 0,
      pageSize: 10,
      sort: {
        empty: true,
        sorted: false,
        unsorted: true,
      },
      offset: 0,
      paged: true,
      unpaged: false,
    },
    last: true,
    totalPages: 0,
    totalElements: 0,
    size: 10,
    number: 0,
    sort: {
      empty: true,
      sorted: true,
      unsorted: true,
    },
    numberOfElements: 0,
    first: true,
    empty: true,
  };
}
